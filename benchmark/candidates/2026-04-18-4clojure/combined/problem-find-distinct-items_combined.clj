(defn problem-find-distinct-items [a]
  (reduce (fn [acc x]
            (if (some #(= x %) acc)
              acc
              (conj acc x)))
          []
          a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-find-distinct-items
  (is (= (= (problem-find-distinct-items [1 2 1 3 1 2 4]) [1 2 3 4])))
  (is (= (= (problem-find-distinct-items [:a :a :b :b :c :c]) [:a :b :c])))
  (is (= (= (problem-find-distinct-items '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))))
  (is (= (= (problem-find-distinct-items (range 50)) (range 50)))))

(run-tests)
