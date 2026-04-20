(defn trim-first-last
  "Remove the first and last occurrence of a given element from a vector.
  
  If the element appears once, remove that single occurrence.
  If it does not appear, return the vector unchanged.
  
  Examples:
  (trim-first-last [1 2 3 2 4] 2) => [1 3 2 4]
  (trim-first-last [:a :b :a :c :a] :a) => [:b :a :c]
  (trim-first-last [1 2 3] 9) => [1 2 3]"
  [v x]
  (let [idxs (keep-indexed (fn [i e] (when (= e x) i)) v)
        first-idx (first idxs)
        last-idx (last idxs)]
    (if (nil? first-idx)
      v
      (vec (concat (subvec (vec v) 0 first-idx)
                   (subvec (vec v) (inc first-idx) last-idx)
                   (subvec (vec v) (inc last-idx)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 2 4] (trim-first-last [1 2 3 2 4] 2))))

(run-test test-variation)
