(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 1 :c 3} (dict_filter_invert {:a 1 :b 2 :c 3} even?)))
  (is (= {:y 10 :z 15} (dict_filter_invert {:x nil :y 10 :z 15} nil?))))

(run-test test-variation)
