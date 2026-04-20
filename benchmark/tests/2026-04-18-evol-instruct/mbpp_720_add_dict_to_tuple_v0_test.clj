(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 2 :b 3} (merge-with-defaults {:a 1} {:a 2 :b 3}))))

(run-test test-variation)
