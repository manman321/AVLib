# AVLib
# ![mahua](http://code.google.com/p/afinal/logo?cct=1351516535) AVLib简介
* AVLib最主要组件是Holder和Data。Holder组件用于简化View的主体（比如Activity、Fragment、Adapter等包含零散View的组件）；Data组件和Holder组件是对应起来的，及一个Holder对应一个Data。目前实现了简单数据和简单View之间的数据自动绑定

#Holder和Data规范
* Holder组件
* 1、需要实现IAvHolder接口：比如独立的Activity对应的Holder
* 2、不需要实现IAvHolder接口：比如Activity直接当做Holder使用
* 3、Holder中的View需要通过@Id注解绑定view
* Data组件
* 1、需要实现IAvData接口：比如独立的Activity对应的Data
* 2、Data中的数据需要通过@DataBind对应Holder的View
