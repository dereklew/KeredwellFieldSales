// Generated code from Butter Knife. Do not modify!
package com.keredwell.fieldsales.ui.customer;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CustomerDetailFragment$$ViewBinder<T extends com.keredwell.fieldsales.ui.customer.CustomerDetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230787, "field 'customername'");
    target.customername = finder.castView(view, 2131230787, "field 'customername'");
    view = finder.findRequiredView(source, 2131230746, "field 'address'");
    target.address = finder.castView(view, 2131230746, "field 'address'");
    view = finder.findRequiredView(source, 2131230877, "field 'postal'");
    target.postal = finder.castView(view, 2131230877, "field 'postal'");
    view = finder.findRequiredView(source, 2131230975, "field 'tel'");
    target.tel = finder.castView(view, 2131230975, "field 'tel'");
    view = finder.findRequiredView(source, 2131230757, "field 'backdropImg'");
    target.backdropImg = finder.castView(view, 2131230757, "field 'backdropImg'");
    view = finder.findRequiredView(source, 2131230782, "field 'collapsingToolbar'");
    target.collapsingToolbar = finder.castView(view, 2131230782, "field 'collapsingToolbar'");
  }

  @Override public void unbind(T target) {
    target.customername = null;
    target.address = null;
    target.postal = null;
    target.tel = null;
    target.backdropImg = null;
    target.collapsingToolbar = null;
  }
}
