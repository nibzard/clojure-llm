(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1.7320508075688772 (weighted-tetrahedron-surface-area {:a 1 :b 1 :c 1 :d 1}))))

(run-test test-variation)
