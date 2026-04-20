(defn problem-set-intersection [a b]
  (reduce (fn [acc x] (if (contains? b x) (conj acc x) acc)) #{} a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-set-intersection
  (is (= (= (problem-set-intersection #{0 1 2 3} #{2 3 4 5}) #{2 3})))
  (is (= (= (problem-set-intersection #{0 1 2} #{3 4 5}) #{})))
  (is (= (= (problem-set-intersection #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d}))))

(run-tests)
