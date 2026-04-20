(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "CLJ" (is_upper "clj")))
  (is (= "HELLO, WORLD!" (is_upper "Hello, World!")))
  (is (= nil (is_upper nil))))

(run-test test-variation)
