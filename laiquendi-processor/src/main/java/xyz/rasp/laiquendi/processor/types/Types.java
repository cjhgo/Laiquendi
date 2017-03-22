package xyz.rasp.laiquendi.processor.types;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;

import java.lang.annotation.Annotation;

/**
 * Created by twiceYuan on 2017/3/21.
 * <p>
 * 用到的 Android 类
 */
public class Types {
    public static final ClassName ANNOTATION_LAYOUT_ID       = ClassName.get("xyz.rasp.laiquendi.core", "ComponentId");
    public static final ClassName ANNOTATION_SUPER_CLASS     = ClassName.get("xyz.rasp.laiquendi.core", "SuperClass");
    public static final ClassName ANNOTATION_PARAM_COMPONENT = ClassName.get("xyz.rasp.laiquendi.core", "ParamsComponent");

    public static final ClassName CONTEXT         = ClassName.get("android.content", "Context");
    public static final ClassName ACTIVITY        = ClassName.get("android.app", "Activity");
    public static final ClassName ATTRIBUTE_SET   = ClassName.get("android.util", "AttributeSet");
    public static final ClassName VIEW            = ClassName.get("android.view", "View");
    public static final ClassName VIEW_GROUP      = ClassName.get("android.view", "ViewGroup");
    public static final ClassName FRAME_LAYOUT    = ClassName.get("android.widget", "FrameLayout");
    public static final ClassName LINEAR_LAYOUT   = ClassName.get("android.widget", "LinearLayout");
    public static final ClassName LAYOUT_INFLATER = ClassName.get("android.view", "LayoutInflater");
    public static final ClassName TYPED_ARRAY     = ClassName.get("android.content.res", "TypedArray");

    public static final AnnotationSpec RESOURCE_TYPE_WARNING = AnnotationSpec.get(new SuppressWarnings() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return SuppressWarnings.class;
        }

        @Override
        public String[] value() {
            return new String[]{"ResourceType"};
        }
    });
}
