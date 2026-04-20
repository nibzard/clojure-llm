(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 4] (remove-kth-from-vector [1 2 3 4] 2)))
  (is (= [:b :c] (remove-kth-from-vector [:a :b :c] 0)))
  (is (= [] (remove-kth-from-vector [] 0)))
  (is (= [1 2 3] (remove-kth-from-vector [1 2 3] 10))))

(run-test test-variation)
