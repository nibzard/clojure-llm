(defn problem-compress-a-sequence [a]
  (map first (partition-by identity a)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-compress-a-sequence
  (is (= (= (apply str (problem-compress-a-sequence "Leeeeeerrroyyy")) "Leroy")))
  (is (= (= (problem-compress-a-sequence [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))))
  (is (= (= (problem-compress-a-sequence [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))))

(run-tests)
