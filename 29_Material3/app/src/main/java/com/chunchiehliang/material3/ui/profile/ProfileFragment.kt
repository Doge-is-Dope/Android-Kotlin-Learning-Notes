package com.chunchiehliang.material3.ui.profile

import android.graphics.Rect
import android.os.Bundle
import android.view.*
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chunchiehliang.material3.databinding.FragmentProfileBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel by viewModel<ProfileViewModel>()

    private var badge: BadgeDrawable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = profileViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                profileViewModel.badgeCount.collect {
                    Timber.d("badge: $it")
                    createBadge(it)
                }
            }
        }

    }

    private fun createBadge(count: Int) {
        Timber.d("count: $count")
        if (badge != null) binding.frameContainer.overlay.remove(badge!!)
        if (count > 0) {
            badge = BadgeDrawable.create(requireContext()).apply {
                isVisible = true
                number = count
                maxCharacterCount = 2
                badgeGravity = BadgeDrawable.TOP_END
            }

            binding.frameContainer.doOnLayout { anchor ->
                val badgeBounds = Rect()
                anchor.getDrawingRect(badgeBounds)
                badge?.bounds = badgeBounds
                badge?.updateBadgeCoordinates(anchor, null)
                badge?.let { anchor.overlay.add(it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}