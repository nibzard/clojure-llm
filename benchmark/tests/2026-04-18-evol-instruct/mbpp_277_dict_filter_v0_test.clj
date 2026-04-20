(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1 :c 3} (dict_filter {:a 1 :b 5 :c 3} 3)))
  (is (= {:b 2} (dict_filter {:a nil :b 2 :c 7} 4))))

(run-test test-variation)
