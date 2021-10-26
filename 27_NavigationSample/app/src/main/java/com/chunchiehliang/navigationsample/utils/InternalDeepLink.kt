package com.chunchiehliang.navigationsample.utils

object InternalDeepLink {
    private const val DOMAIN = "popchill://"
    private const val PRODUCT = "${DOMAIN}product"
    private const val USER_PREFIX = "${DOMAIN}user/"
    private const val ORDER_LIST = "${DOMAIN}order-list"
    private const val ORDER = "${DOMAIN}order"

    fun getUserDeepLink(username: String, tabType: String? = "closet") =
        "${USER_PREFIX}${username}?tabType=${tabType}"
}