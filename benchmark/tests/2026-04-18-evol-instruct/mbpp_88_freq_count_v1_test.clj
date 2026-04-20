(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 2, :b 2, :c 1}
         (freq_count_no_nil [:a :b :a nil :b :c nil]))))

(run-test test-variation)
