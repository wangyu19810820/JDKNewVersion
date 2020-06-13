module module2 {
    requires module1;
    requires java.logging;
    requires junit;
    exports jdk9test;
}