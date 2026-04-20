(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:a :b :c] (sort-keys-by-value {:c 3, :a 1, :b 2}))))

(run-test test-variation)
