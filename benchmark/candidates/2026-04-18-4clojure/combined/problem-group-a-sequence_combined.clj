(defn problem-group-a-sequence [a b]
  (reduce (fn [m x]
            (let [k (a x)]
              (assoc m k (conj (get m k []) x))))
          {}
          b))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-group-a-sequence
  (is (= (= (problem-group-a-sequence #(> % 5) #{1 3 6 8}) {false [1 3], true [6 8]})))
  (is (= (= (problem-group-a-sequence #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})))
  (is (= (= (problem-group-a-sequence count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}))))

(run-tests)
