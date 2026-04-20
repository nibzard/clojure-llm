(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 9, :b 3, :c 4, :d 5}
         (merge-with-precedence {:a 1 :b 2}
                                {:b 3 :c 4}
                                {:a 9 :d 5}))))

(run-test test-variation)
