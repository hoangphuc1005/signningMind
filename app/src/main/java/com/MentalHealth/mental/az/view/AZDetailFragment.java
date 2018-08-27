package com.MentalHealth.mental.az.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.MentalHealth.mental.R;
import com.MentalHealth.mental.az.model.AZChildModel;
import com.MentalHealth.mental.az.model.AZGroupModel;
import com.MentalHealth.mental.base.BaseFragment;

import java.util.ArrayList;


public class AZDetailFragment extends BaseFragment {
    private ImageView imgAZ;
    private ArrayList<AZGroupModel> listGroup;
    private AZGroupModel modelGroup;
    private AZChildModel modelChild;
    private ArrayList<AZChildModel> listChildModels;
    private ArrayList<AZChildModel> listChildModels1;
    private ArrayList<AZChildModel> listChildModels2;
    private ArrayList<AZChildModel> listChildModels3;
    private ArrayList<AZChildModel> listChildModels4;
    private ArrayList<AZChildModel> listChildModels5;
    private ArrayList<AZChildModel> listChildModels6;
    private ExpandableListView expandableAZ;
    private AZDetailAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_a_z_detail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        actionView();
        setTitleActionBar("Trầm cảm");
        updateBackActionbar();
    }

    private void actionView() {
        adapter = new AZDetailAdapter(getContext(), listGroup);
        expandableAZ.setAdapter(adapter);
    }

    private void initView() {
        imgAZ = (ImageView) findViewById(R.id.img_a_z_detail);
        checkData();
        listChildModels = new ArrayList<>();
        listChildModels1 = new ArrayList<>();
        listChildModels2 = new ArrayList<>();
        listChildModels3 = new ArrayList<>();
        listChildModels4 = new ArrayList<>();
        listChildModels5 = new ArrayList<>();
        listChildModels6 = new ArrayList<>();
        listGroup = new ArrayList<>();
        modelChild = new AZChildModel("Nỗi buồn là điều mà tất cả chúng ta đều trải qua. Đó là một cách phản ứng bình thường khi con người gặp những thời điểm khó khăn trong cuộc sống và  nó thường trôi qua sau một ít thời gian.\n" +
                "Khi một người bị trầm cảm, nó cản trở cuộc sống hàng ngày của họ. Nó có thể gây ra sự đau khổ cho cả người trầm cảm và những người chăm sóc  họ. Nỗi buồn chỉ là một phần rất nhỏ của trầm cảm. Các bác sĩ gọi tình trạng này là “Rối loạn trầm cảm” hoặc “Trầm cảm lâm sàng”. Đó là một căn bệnh thực sự. Nó không phải là dấu hiệu của sự yếu đuối ở một người. Bạn không thể tự “thoát khỏi” rối loạn trầm cảm mà hầu hết những người bị trầm cảm cần điều trị để trở nên tốt hơn.\n" +
                "Trầm cảm là một rối loạn khí sắc (mood disorder) nghiêm trọng. Nó gây ra các triệu trứng nghiêm trọng ảnh hưởng đến cách bạn cảm nhận, suy nghĩ và xử lý các hoạt động hàng ngày như ngủ, ăn hoặc làm việc\n");
        listChildModels.add(modelChild);
        modelGroup = new AZGroupModel("Rối loạn trầm cảm là gì ?", listChildModels);
        listGroup.add(modelGroup);

        modelChild = new AZChildModel("Nỗi buồn chỉ là một phần nhỏ của trầm cảm. Một số người bị trầm cảm có thể không cảm thấy buồn. Trầm cảm có nhiều triệu chứng khác, bao gồm các triệu chứng thể chất. Nếu bạn đã trải qua bất kỳ dấu hiệu và triệu trứng nào sau đây trong ít nhất 2 tuần, bạn có thể bị trầm cảm:\n" +
                "- Tâm trạng buồn bã, lo lắng hoặc “trống rỗng” liên tục\n" +
                "- Cảm giác tuyệt vọng, bi quan.\n" +
                "- Cáu gắt\n" +
                "- Cảm giác tội lỗi, vô gia trị, bất lực\n" +
                "- Mất hứng thú với các sở thích và hoạt động\n" +
                "- Giảm năng lượng, mệt mỏi, cảm thấy bị chậm chạp như di chuyển chậm-nói chậm hơn\n" +
                "- Khó tập trung, ghi nhớ và ra quyết định\n" +
                "- Khó ngủ, hay thức dậy vào buổi sáng sớm hoặc ngủ quá nhiều\n" +
                "- Có sự thay đổi trong cân nặng hoặc sự thèm ăn\n" +
                "- Suy nghĩ về cái chết hoặc tự tử hoặc cố gắng tự tử\n" +
                "- Cảm thấy bồn chồn, khó chịu\n" +
                "- Nhức và đau, đau đầu, chuột rút hoặc có các vấn đề về tiêu hóa mà không có nguyên nhân thể chất rõ \n" +
                "Không phải bất kỳ ai bị trầm cảm đều trải nghiệm mọi triệu chứng. Một số người trải qua một vài triệu chứng trong khi có những người khác có thể gặp nhiều triệu chứng hơn. Mức độ nghiêm trọng, tuần suất của các triệu chứng và thời gian kéo dài bao lâu tùy thuộc vào từng cá nhân và bệnh tật cụ thể của họ. Các triệu chứng cũng có thể thay đổi theo đừng giai đoạn của bệnh.\n");
        listChildModels1.add(modelChild);


        modelGroup = new AZGroupModel("Triệu chứng của bệnh", listChildModels1);
        listGroup.add(modelGroup);
        modelChild = new AZChildModel("Nếu các triệu chứng trầm cảm đang cản trở khả năng hoạt động và cuộc sống của bạn, hãy nhờ sự trợ giúp. Trầm cảm có thể trở nên tốt hơn với việc chăm sóc và điều trị. Đừng đợi trầm cảm tự biến mất hoặc nghĩ bạn có thể tự quản lý nó và đừng bỏ qua cảm giác của bạn chỉ vì bạn nghĩ rằng bạn có thể “Giải thích” nó. Nếu bạn không yêu cầu sự giúp đỡ, trầm cảm có thể trở nên tồi tệ hơn và có thể làm phát sinh ra các vấn đề sức khỏe khác.\n" +
                "Trầm cảm- ngay cả những trường hợp nặng nhất, có thể được điều trị. Việc điều trị bắt đầu sớm sẽ hiệu quả hơn. Hầu hết người trưởng thành thấy sự cải thiện trong các triệu chứng của họ khi được điều trị bằng thuốc chống trầm cẩm, trị liệu tâm lý hoặc kết hợp cả hai.\n" +
                "Hãy nhớ rằng: Không có ai bị ảnh hưởng theo cùng một cách bởi trần cảm. Không có “một kích thước phù hợp tất cả” để điều trị. Có thể mất một số thử nghiệm và sai sót để tìm ra cách điều trị phù hợp nhất với bạn.\n" +
                "Thuốc\n" +
                "Thuốc chống trầm cảm là loại thuốc điều trị trầm cảm. Chúng có thể giúp cải thiện cách não bộ của bạn sử dụng một số chất hóa học kiểm soát tâm trạng hoặc căng thẳng.\n" +
                "Có một số loại thuốc chống trầm cảm: \n" +
                "- Các chất ức chế tái hấp thu serotonin (SSRI)\n" +
                "- Thuốc ức chế tái hấp thu serotonin và norepinephrine (SNRI)\n" +
                "- Thuốc chống trầm cảm ba vòng (TCA)\n" +
                "- Chất ức chế monoamine oxidase (MAOI)\n" +
                "Có những thuốc chống trầm cảm khác không thuộc bất kỳ loại nào trong số này và được coi là duy nhất như Mirtazapine và Bupropion.\n" +
                "Mặc dù tất cả các thuốc chống trầm cảm đều có thể gây ra các tác dụng phụ, nhưng một số thuốc có nhiều khả năng gây tác dụng phụ nhiều hơn ở những người khác. Bạn có thể cần phải thử một số loại thuốc chống trầm cảm khác nhau trước khi tìm một loại thuốc cải thiện triệu chứng của bạn và có tác dụng phụ mà bạn có thể kiểm soát.\n" +
                "Những người dùng thuốc chống trầm cảm cần phải làm theo chỉ dẫn của bác sĩ. Thuốc nên được dùng đúng liều và đúng thời gian. Thuốc chống trầm cảm mất thời gian – thường là 2 đến 4 tuần để có tác dụng. Các triệu chứng như giấc ngủ, sự thèm ăn và các vấn đề tập trung cải thiện trước khi tâm trạng được cải thiện. Vì vậy điều quan trọng là phải cho thuốc một cơ hội trước khi kết luận về hiệu quả của thuốc.\n" +
                "Nếu bạn bắt đầu uống thuốc chống trầm cảm đừng ngưng dừng thuốc khi không có chỉ định của bác sĩ. Vì đôi khi những người dùng thuốc chống trầm cảm cảm thấy đỡ hơn, sau đó tự ý ngưng thuốc và trầm cảm bị lại. Khi bạn và bác sĩ quyết định ngừng thuốc thường là sau 6-12 tháng. Khi đó bác sĩ sẽ giúp bạn từ từ và giảm liều một cách an toàn. Dừng đột ngột có thể gây ra triệu chứng cai nghiện.\n" +
                "Tại Mỹ, thông tin về thuốc có thể tìm thấy dễ dàng trên trang Web của  Cơ quan Quản lý Thực phẩm và Dược phẩm (FDA- Food and Drug Administration www.fda.gov). Các thông tin tại đây được kiểm định và cập nhật thường xuyên.\n");
        listChildModels2.add(modelChild);


        modelGroup = new AZGroupModel("Phân Loại ", listChildModels2);
        listGroup.add(modelGroup);
        modelChild = new AZChildModel("Nếu các triệu chứng trầm cảm đang cản trở khả năng hoạt động và cuộc sống của bạn, hãy nhờ sự trợ giúp. Trầm cảm có thể trở nên tốt hơn với việc chăm sóc và điều trị. Đừng đợi trầm cảm tự biến mất hoặc nghĩ bạn có thể tự quản lý nó và đừng bỏ qua cảm giác của bạn chỉ vì bạn nghĩ rằng bạn có thể “Giải thích” nó. Nếu bạn không yêu cầu sự giúp đỡ, trầm cảm có thể trở nên tồi tệ hơn và có thể làm phát sinh ra các vấn đề sức khỏe khác.\n" +
                "Trầm cảm- ngay cả những trường hợp nặng nhất, có thể được điều trị. Việc điều trị bắt đầu sớm sẽ hiệu quả hơn. Hầu hết người trưởng thành thấy sự cải thiện trong các triệu chứng của họ khi được điều trị bằng thuốc chống trầm cẩm, trị liệu tâm lý hoặc kết hợp cả hai.\n" +
                "Hãy nhớ rằng: Không có ai bị ảnh hưởng theo cùng một cách bởi trần cảm. Không có “một kích thước phù hợp tất cả” để điều trị. Có thể mất một số thử nghiệm và sai sót để tìm ra cách điều trị phù hợp nhất với bạn.\n" +
                "Thuốc\n" +
                "Thuốc chống trầm cảm là loại thuốc điều trị trầm cảm. Chúng có thể giúp cải thiện cách não bộ của bạn sử dụng một số chất hóa học kiểm soát tâm trạng hoặc căng thẳng.\n" +
                "Có một số loại thuốc chống trầm cảm: \n" +
                "- Các chất ức chế tái hấp thu serotonin (SSRI)\n" +
                "- Thuốc ức chế tái hấp thu serotonin và norepinephrine (SNRI)\n" +
                "- Thuốc chống trầm cảm ba vòng (TCA)\n" +
                "- Chất ức chế monoamine oxidase (MAOI)\n" +
                "Có những thuốc chống trầm cảm khác không thuộc bất kỳ loại nào trong số này và được coi là duy nhất như Mirtazapine và Bupropion.\n" +
                "Mặc dù tất cả các thuốc chống trầm cảm đều có thể gây ra các tác dụng phụ, nhưng một số thuốc có nhiều khả năng gây tác dụng phụ nhiều hơn ở những người khác. Bạn có thể cần phải thử một số loại thuốc chống trầm cảm khác nhau trước khi tìm một loại thuốc cải thiện triệu chứng của bạn và có tác dụng phụ mà bạn có thể kiểm soát.\n" +
                "Những người dùng thuốc chống trầm cảm cần phải làm theo chỉ dẫn của bác sĩ. Thuốc nên được dùng đúng liều và đúng thời gian. Thuốc chống trầm cảm mất thời gian – thường là 2 đến 4 tuần để có tác dụng. Các triệu chứng như giấc ngủ, sự thèm ăn và các vấn đề tập trung cải thiện trước khi tâm trạng được cải thiện. Vì vậy điều quan trọng là phải cho thuốc một cơ hội trước khi kết luận về hiệu quả của thuốc.\n" +
                "Nếu bạn bắt đầu uống thuốc chống trầm cảm đừng ngưng dừng thuốc khi không có chỉ định của bác sĩ. Vì đôi khi những người dùng thuốc chống trầm cảm cảm thấy đỡ hơn, sau đó tự ý ngưng thuốc và trầm cảm bị lại. Khi bạn và bác sĩ quyết định ngừng thuốc thường là sau 6-12 tháng. Khi đó bác sĩ sẽ giúp bạn từ từ và giảm liều một cách an toàn. Dừng đột ngột có thể gây ra triệu chứng cai nghiện.\n" +
                "Tại Mỹ, thông tin về thuốc có thể tìm thấy dễ dàng trên trang Web của  Cơ quan Quản lý Thực phẩm và Dược phẩm (FDA- Food and Drug Administration www.fda.gov). Các thông tin tại đây được kiểm định và cập nhật thường xuyên.\n");
        listChildModels3.add(modelChild);


        modelGroup = new AZGroupModel("Trị Liệu", listChildModels3);
        listGroup.add(modelGroup);
        modelChild = new AZChildModel("Nếu các triệu chứng trầm cảm đang cản trở khả năng hoạt động và cuộc sống của bạn, hãy nhờ sự trợ giúp. Trầm cảm có thể trở nên tốt hơn với việc chăm sóc và điều trị. Đừng đợi trầm cảm tự biến mất hoặc nghĩ bạn có thể tự quản lý nó và đừng bỏ qua cảm giác của bạn chỉ vì bạn nghĩ rằng bạn có thể “Giải thích” nó. Nếu bạn không yêu cầu sự giúp đỡ, trầm cảm có thể trở nên tồi tệ hơn và có thể làm phát sinh ra các vấn đề sức khỏe khác.\n" +
                "Trầm cảm- ngay cả những trường hợp nặng nhất, có thể được điều trị. Việc điều trị bắt đầu sớm sẽ hiệu quả hơn. Hầu hết người trưởng thành thấy sự cải thiện trong các triệu chứng của họ khi được điều trị bằng thuốc chống trầm cẩm, trị liệu tâm lý hoặc kết hợp cả hai.\n" +
                "Hãy nhớ rằng: Không có ai bị ảnh hưởng theo cùng một cách bởi trần cảm. Không có “một kích thước phù hợp tất cả” để điều trị. Có thể mất một số thử nghiệm và sai sót để tìm ra cách điều trị phù hợp nhất với bạn.\n" +
                "Thuốc\n" +
                "Thuốc chống trầm cảm là loại thuốc điều trị trầm cảm. Chúng có thể giúp cải thiện cách não bộ của bạn sử dụng một số chất hóa học kiểm soát tâm trạng hoặc căng thẳng.\n" +
                "Có một số loại thuốc chống trầm cảm: \n" +
                "- Các chất ức chế tái hấp thu serotonin (SSRI)\n" +
                "- Thuốc ức chế tái hấp thu serotonin và norepinephrine (SNRI)\n" +
                "- Thuốc chống trầm cảm ba vòng (TCA)\n" +
                "- Chất ức chế monoamine oxidase (MAOI)\n" +
                "Có những thuốc chống trầm cảm khác không thuộc bất kỳ loại nào trong số này và được coi là duy nhất như Mirtazapine và Bupropion.\n" +
                "Mặc dù tất cả các thuốc chống trầm cảm đều có thể gây ra các tác dụng phụ, nhưng một số thuốc có nhiều khả năng gây tác dụng phụ nhiều hơn ở những người khác. Bạn có thể cần phải thử một số loại thuốc chống trầm cảm khác nhau trước khi tìm một loại thuốc cải thiện triệu chứng của bạn và có tác dụng phụ mà bạn có thể kiểm soát.\n" +
                "Những người dùng thuốc chống trầm cảm cần phải làm theo chỉ dẫn của bác sĩ. Thuốc nên được dùng đúng liều và đúng thời gian. Thuốc chống trầm cảm mất thời gian – thường là 2 đến 4 tuần để có tác dụng. Các triệu chứng như giấc ngủ, sự thèm ăn và các vấn đề tập trung cải thiện trước khi tâm trạng được cải thiện. Vì vậy điều quan trọng là phải cho thuốc một cơ hội trước khi kết luận về hiệu quả của thuốc.\n" +
                "Nếu bạn bắt đầu uống thuốc chống trầm cảm đừng ngưng dừng thuốc khi không có chỉ định của bác sĩ. Vì đôi khi những người dùng thuốc chống trầm cảm cảm thấy đỡ hơn, sau đó tự ý ngưng thuốc và trầm cảm bị lại. Khi bạn và bác sĩ quyết định ngừng thuốc thường là sau 6-12 tháng. Khi đó bác sĩ sẽ giúp bạn từ từ và giảm liều một cách an toàn. Dừng đột ngột có thể gây ra triệu chứng cai nghiện.\n" +
                "Tại Mỹ, thông tin về thuốc có thể tìm thấy dễ dàng trên trang Web của  Cơ quan Quản lý Thực phẩm và Dược phẩm (FDA- Food and Drug Administration www.fda.gov). Các thông tin tại đây được kiểm định và cập nhật thường xuyên.\n");
        listChildModels4.add(modelChild);

        modelGroup = new AZGroupModel("Tự trợ giúp", listChildModels4);
        listGroup.add(modelGroup);
        modelChild = new AZChildModel("Nếu các triệu chứng trầm cảm đang cản trở khả năng hoạt động và cuộc sống của bạn, hãy nhờ sự trợ giúp. Trầm cảm có thể trở nên tốt hơn với việc chăm sóc và điều trị. Đừng đợi trầm cảm tự biến mất hoặc nghĩ bạn có thể tự quản lý nó và đừng bỏ qua cảm giác của bạn chỉ vì bạn nghĩ rằng bạn có thể “Giải thích” nó. Nếu bạn không yêu cầu sự giúp đỡ, trầm cảm có thể trở nên tồi tệ hơn và có thể làm phát sinh ra các vấn đề sức khỏe khác.\n" +
                "Trầm cảm- ngay cả những trường hợp nặng nhất, có thể được điều trị. Việc điều trị bắt đầu sớm sẽ hiệu quả hơn. Hầu hết người trưởng thành thấy sự cải thiện trong các triệu chứng của họ khi được điều trị bằng thuốc chống trầm cẩm, trị liệu tâm lý hoặc kết hợp cả hai.\n" +
                "Hãy nhớ rằng: Không có ai bị ảnh hưởng theo cùng một cách bởi trần cảm. Không có “một kích thước phù hợp tất cả” để điều trị. Có thể mất một số thử nghiệm và sai sót để tìm ra cách điều trị phù hợp nhất với bạn.\n" +
                "Thuốc\n" +
                "Thuốc chống trầm cảm là loại thuốc điều trị trầm cảm. Chúng có thể giúp cải thiện cách não bộ của bạn sử dụng một số chất hóa học kiểm soát tâm trạng hoặc căng thẳng.\n" +
                "Có một số loại thuốc chống trầm cảm: \n" +
                "- Các chất ức chế tái hấp thu serotonin (SSRI)\n" +
                "- Thuốc ức chế tái hấp thu serotonin và norepinephrine (SNRI)\n" +
                "- Thuốc chống trầm cảm ba vòng (TCA)\n" +
                "- Chất ức chế monoamine oxidase (MAOI)\n" +
                "Có những thuốc chống trầm cảm khác không thuộc bất kỳ loại nào trong số này và được coi là duy nhất như Mirtazapine và Bupropion.\n" +
                "Mặc dù tất cả các thuốc chống trầm cảm đều có thể gây ra các tác dụng phụ, nhưng một số thuốc có nhiều khả năng gây tác dụng phụ nhiều hơn ở những người khác. Bạn có thể cần phải thử một số loại thuốc chống trầm cảm khác nhau trước khi tìm một loại thuốc cải thiện triệu chứng của bạn và có tác dụng phụ mà bạn có thể kiểm soát.\n" +
                "Những người dùng thuốc chống trầm cảm cần phải làm theo chỉ dẫn của bác sĩ. Thuốc nên được dùng đúng liều và đúng thời gian. Thuốc chống trầm cảm mất thời gian – thường là 2 đến 4 tuần để có tác dụng. Các triệu chứng như giấc ngủ, sự thèm ăn và các vấn đề tập trung cải thiện trước khi tâm trạng được cải thiện. Vì vậy điều quan trọng là phải cho thuốc một cơ hội trước khi kết luận về hiệu quả của thuốc.\n" +
                "Nếu bạn bắt đầu uống thuốc chống trầm cảm đừng ngưng dừng thuốc khi không có chỉ định của bác sĩ. Vì đôi khi những người dùng thuốc chống trầm cảm cảm thấy đỡ hơn, sau đó tự ý ngưng thuốc và trầm cảm bị lại. Khi bạn và bác sĩ quyết định ngừng thuốc thường là sau 6-12 tháng. Khi đó bác sĩ sẽ giúp bạn từ từ và giảm liều một cách an toàn. Dừng đột ngột có thể gây ra triệu chứng cai nghiện.\n" +
                "Tại Mỹ, thông tin về thuốc có thể tìm thấy dễ dàng trên trang Web của  Cơ quan Quản lý Thực phẩm và Dược phẩm (FDA- Food and Drug Administration www.fda.gov). Các thông tin tại đây được kiểm định và cập nhật thường xuyên.\n");
        listChildModels5.add(modelChild);

        modelGroup = new AZGroupModel("Trích nguồn", listChildModels5);
        listGroup.add(modelGroup);

        modelChild = new AZChildModel("https://www.nimh.nih.gov/health/topics/depression/index.shtml\n" +
                "https://www.nimh.nih.gov/health/publications/teen-depression/index.shtml\n" +
                "https://www.nimh.nih.gov/health/publications/depression-what-you-need-to-know/index.shtml \n" +
                "http://www.who.int/campaigns/world-health-day/2017/campaign-essentials/en/\n");
        listChildModels6.add(modelChild);
        listGroup.add(modelGroup);
        expandableAZ = (ExpandableListView) findViewById(R.id.expanded_a_z_Detail);
    }

    private void checkData() {
        Bundle bundle = getArguments();
        String style = bundle.getString("a_z");
        if (style == "a") {
            imgAZ.setImageResource(R.drawable.img_anhtotramcam);
        }
        if (style == "b") {
            imgAZ.setImageResource(R.drawable.img_rlcx);
        }

        if (style == "c") {
            imgAZ.setImageResource(R.drawable.img_rlla);
        }
    }
}
