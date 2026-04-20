(defn common-element-indexes
  "Return a vector of [i j] pairs for every shared element between two vectors, where i is the index in v1 and j is the index in v2.

  Examples:
  (common-element-indexes [1 2 3] [4 2 5]) ;=> [[1 1]]
  (common-element-indexes [:a :b :a] [:c :a]) ;=> [[0 1] [2 1]]
  (common-element-indexes [] [1 2]) ;=> []"
  [v1 v2]
  (vec
   (for [i (range (count v1))
         j (range (count v2))
         :when (= (nth v1 i) (nth v2 j))]
     [i j])))