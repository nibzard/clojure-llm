(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (same-multiset? [1 2 2 3] '(3 2 1 2))))
  (is (= false (same-multiset? [:a :b] [:b :a :a])))
  (is (= true (same-multiset? nil []))))

(run-test test-variation)
