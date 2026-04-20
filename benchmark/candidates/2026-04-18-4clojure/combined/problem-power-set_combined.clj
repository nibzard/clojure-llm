(defn problem-power-set [a]
  (reduce (fn [ps x]
            (into ps (map #(conj % x) ps)))
          #{#{}}
          a))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-power-set
  (is (= (= (problem-power-set #{1 :a}) #{#{1 :a} #{:a} #{} #{1}})))
  (is (= (= (problem-power-set #{}) #{#{}})))
  (is (= (= (problem-power-set #{1 2 3})
   #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})))
  (is (= (= (count (problem-power-set (into #{} (range 10)))) 1024))))

(run-tests)
