package jp.pois.oxacillin.extensions.models

import jp.pois.oxacillin.extensions.User


/**
 * Whether if this account is locked.
 */
public inline val User.isLockedAccount: Boolean
    get() = profileInterstitialType == "fake_account"