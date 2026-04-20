(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:c 3, :b 2, :a 1} (sort-by-key-desc {:b 2, :a 1, :c 3}))))

(run-test test-variation)
