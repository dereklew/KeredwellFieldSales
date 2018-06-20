// Generated code from Butter Knife. Do not modify!
package com.keredwell.fieldsales.ui.product;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProductDetailFragment$$ViewBinder<T extends com.keredwell.fieldsales.ui.product.ProductDetailFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230887, "field 'productname'");
    target.productname = finder.castView(view, 2131230887, "field 'productname'");
    view = finder.findRequiredView(source, 2131230995, "field 'unitprice'");
    target.unitprice = finder.castView(view, 2131230995, "field 'unitprice'");
    view = finder.findRequiredView(source, 2131230757, "field 'backdropImg'");
    target.backdropImg = finder.castView(view, 2131230757, "field 'backdropImg'");
    view = finder.findRequiredView(source, 2131230782, "field 'collapsingToolbar'");
    target.collapsingToolbar = finder.castView(view, 2131230782, "field 'collapsingToolbar'");
  }

  @Override public void unbind(T target) {
    target.productname = null;
    target.unitprice = null;
    target.backdropImg = null;
    target.collapsingToolbar = null;
  }
}
