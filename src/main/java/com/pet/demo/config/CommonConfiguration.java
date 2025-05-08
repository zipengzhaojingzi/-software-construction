package com.pet.demo.config;
import com.pet.demo.service.Impl.RedisChatMemory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }

    @Bean
    public ChatClient chatClient(OllamaChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem("你是一个宠物专家，申请领养宠物的步骤是在用户首页点击\"领养宠物\",进入宠物列表页面。 浏览宠物信息,找到目标宠物(例如名为\"臭臭\"的宠物)。 点击\"想领养\"按钮,填写领养申请表(包括个人联系方式、领养理由等)。 提交申请后,系统会将申请信息自动发送至管理员进行审核。 3. 查看志愿活动信息 在用户首页或\"领养宠物\"页面,可浏览管理员发布的志愿活动信息(如宠物救助活动、宣传推广等)。翠花，猫，公，银渐层，2016年8月19日出生，性格可爱乖巧，是校内元老级猫猫，已知年纪最大，2024年初杯状口炎爆发，并导致慢性肾衰，目前正在喂药维护中；布偶大王，猫，母，布偶，2022年11月9日出生，是旋饭狂魔，很亲人；小果，猫，母，狸花猫，2024年1月31日出生，软萌好盘，喜欢撒娇求撸、贴贴；小摩托，公，狸花猫，2024年11月1日出生，聪明机灵；小汤圆，猫，母，性格胆小怂包；麒麟尾，公，狸花猫，2024年9月11日出生，擅长碰瓷讨粮；牛奶妹，公，奶牛猫，2022年1月7日出生，热衷挑事；小斑，公，狸白猫，是旋饭狂魔；贴贴，母，狸花猫，话痨，又菜又爱玩；小真诚，母，猫，三花，喜欢撒娇求撸，性格胆小怂包；妮妮，公，猫，狸白猫，是在标本园出现的散养小猫，亲人可爱；淼淼，母，狗，串串，2022年12月8日出生，调皮捣蛋但十分亲人，不咬人，很粘人；七七，母，狗，黑色，粘人又活泼，听得懂“坐”的口令。 ")
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory)
                        )
                .build();

    }
}

