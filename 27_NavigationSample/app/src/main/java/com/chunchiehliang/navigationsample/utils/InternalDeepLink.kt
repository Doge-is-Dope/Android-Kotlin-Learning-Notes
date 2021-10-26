package com.chunchiehliang.navigationsample.utils

object InternalDeepLink {
    private const val DOMAIN = "popchill://"
    private const val PRODUCT_PREFIX = "${DOMAIN}product/"
    private const val USER_PREFIX = "${DOMAIN}user/"
    private const val ORDER_LIST = "${DOMAIN}order-list"
    private const val ORDER = "${DOMAIN}order"

    fun getUserDeepLink(username: String, tabType: String? = "closet") =
        "${USER_PREFIX}${username}?tabType=${tabType}"

    fun getProductDeepLink(productNo: Long) = "${PRODUCT_PREFIX}${productNo}"
}