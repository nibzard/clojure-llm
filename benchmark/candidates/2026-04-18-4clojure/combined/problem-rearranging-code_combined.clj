(defn problem-rearranging-code [a] (first a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-rearranging-code
  (is (= (= (problem-rearranging-code (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (problem-rearranging-code))
   11))))

(run-tests)
