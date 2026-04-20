(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-occurrence [1 2 2 3 3])))
  (is (= :a (max-occurrence [:a :b :a :b])))
  (is (= nil (max-occurrence nil))))

(run-test test-variation)
