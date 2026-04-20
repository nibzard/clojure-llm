(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "kiwi" (min-by-key count ["pear" "apple" "kiwi"]))))

(run-test test-variation)
