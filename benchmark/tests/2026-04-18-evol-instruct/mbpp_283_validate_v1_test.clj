(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (validate {:a [:b :b :c] :b [:a] :c nil})))
  (is (= false (validate {:a [:b :b :b] :b [:a]})))
  (is (= true (validate {:c [:a :a :a]})))
  (is (= true (validate {}))))

(run-test test-variation)
