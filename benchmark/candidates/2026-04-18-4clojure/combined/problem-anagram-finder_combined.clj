(defn problem-anagram-finder [a]
  (->> a
       (group-by #(sort %))
       vals
       (map set)
       (filter #(> (count %) 1))
       set))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-anagram-finder
  (is (= (= (problem-anagram-finder ["meat" "mat" "team" "mate" "eat"])
   #{#{"meat" "team" "mate"}})))
  (is (= (= (problem-anagram-finder ["veer" "lake" "item" "kale" "mite" "ever"])
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))))

(run-tests)
