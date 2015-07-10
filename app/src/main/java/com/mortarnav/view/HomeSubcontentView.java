package com.mortarnav.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.mortarnav.R;
import com.mortarnav.presenter.HomeSubcontentPresenter;
import com.mortarnav.presenter.stackable.HomeSubcontentStackable;
import com.mortarnav.presenter.stackable.HomeSubcontentStackableComponent;

import architect.Stackable;
import architect.commons.view.StackedLinearLayout;
import architect.robot.DaggerService;
import autodagger.AutoInjector;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lukasz Piliszczuk - lukasz.pili@gmail.com
 */
@AutoInjector(HomeSubcontentPresenter.class)
public class HomeSubcontentView extends StackedLinearLayout<HomeSubcontentPresenter> {

    @Bind(R.id.home_sub_random)
    public TextView randomTextView;

    public HomeSubcontentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Stackable getStackable() {
        return new HomeSubcontentStackable();
    }


    @Override
    public void initWithContext(Context context) {
        DaggerService.<HomeSubcontentStackableComponent>get(context).inject(this);

        View view = View.inflate(context, R.layout.home_subcontent_view, this);
        ButterKnife.bind(view);
    }

    @OnClick
    void click() {
        presenter.click();
    }
}
