(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (contains-in-order? [1 3] [1 2 3 4])))
  (is (= true (contains-in-order? [:a :c] [:a :b :c])))
  (is (= false (contains-in-order? [2 1] [1 2 3])))
  (is (= true (contains-in-order? [] [1 2 3]))))

(run-test test-variation)
