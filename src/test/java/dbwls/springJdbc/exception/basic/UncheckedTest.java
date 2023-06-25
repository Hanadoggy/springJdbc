package dbwls.springJdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class UncheckedTest {

    @Test
    void uncheckedCatch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void uncheckedThrow() {
        Service service = new Service();
        assertThatThrownBy(service::callThrow)
                .isInstanceOf(MyUncheckedException.class);
    }

    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    /**
     * Unchecked Exception은 예외를 잡거나 던지지 않아도 됨
     * 예외를 잡지 않으면 자동으로 밖으로 던짐
     */
    static class Service {
        Repository repository = new Repository();

        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("예외 처리, message = {}", e.getMessage(), e);
            }
        }

        public void callThrow() {
            repository.call();
        }
    }


    static class Repository {
        public void call() {
            throw new MyUncheckedException("ex");
        }
    }
}
