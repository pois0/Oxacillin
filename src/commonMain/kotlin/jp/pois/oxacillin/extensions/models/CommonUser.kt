package jp.pois.oxacillin.extensions.models

import jp.pois.oxacillin.extensions.CommonUser


/**
 * Whether if this account is locked.
 */
public inline val CommonUser.isLockedAccount: Boolean
    get() = profileInterstitialType == "fake_account"