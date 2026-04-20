(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (parse_base "22" 3)))
  (is (= 8 (parse_base "1000" 2)))
  (is (= 255 (parse_base "ff" 16)))
  (is (= nil (parse_base nil 10))))

(run-test test-variation)
