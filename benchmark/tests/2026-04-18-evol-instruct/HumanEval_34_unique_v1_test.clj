(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["a" "bb" "eee"] (unique-by count ["a" "bb" "c" "dd" "eee" "f"]))))

(run-test test-variation)
