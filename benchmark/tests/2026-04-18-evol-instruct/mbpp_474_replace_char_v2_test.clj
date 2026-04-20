(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 9 3] (replace-nth [1 2 3] 1 9)))
  (is (= [:z :b :c] (replace-nth [:a :b :c] 0 :z)))
  (is (= [1 2 3] (replace-nth [1 2 3] 5 9))))

(run-test test-variation)
