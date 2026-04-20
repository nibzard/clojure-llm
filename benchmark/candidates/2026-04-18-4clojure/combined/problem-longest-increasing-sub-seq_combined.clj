(defn problem-longest-increasing-sub-seq [a]
  (let [[best curr]
        (reduce
         (fn [[best curr] x]
           (if (or (empty? curr) (< (peek curr) x))
             [best (conj curr x)]
             [(if (> (count curr) (count best)) curr best) [x]]))
         [[] []]
         a)
        best (if (> (count curr) (count best)) curr best)]
    (if (>= (count best) 2) best [])))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-longest-increasing-sub-seq
  (is (= (= (problem-longest-increasing-sub-seq [1 0 1 2 3 0 4 5]) [0 1 2 3])))
  (is (= (= (problem-longest-increasing-sub-seq [5 6 1 3 2 7]) [5 6])))
  (is (= (= (problem-longest-increasing-sub-seq [2 3 3 4 5]) [3 4 5])))
  (is (= (= (problem-longest-increasing-sub-seq [7 6 5 4]) []))))

(run-tests)
