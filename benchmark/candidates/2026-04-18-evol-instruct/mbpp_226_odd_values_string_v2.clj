(defn odd-index-values
  "Return a vector containing the values at odd indexes from the given vector.

  Examples:
  (odd-index-values [1 2 3 4 5]) => [2 4]
  (odd-index-values [:a :b :c :d]) => [:b :d]
  (odd-index-values []) => []"
  [v]
  (vec (keep-indexed (fn [i x] (when (odd? i) x)) v)))