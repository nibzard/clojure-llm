(defn max-occurrence-path
  "Given a nested map, return the key path to the leaf value that occurs most frequently.
  Paths are returned as vectors of keys. If multiple leaf values tie for maximum frequency,
  return the first path encountered in a depth-first, left-to-right traversal.

  Examples:
  (max-occurrence-path {:a 1 :b 2 :c 1})
  ;; => [:a]

  (max-occurrence-path {:x {:y 3} :z {:w 3 :u 4}})
  ;; => [:x :y]

  (max-occurrence-path nil)
  ;; => nil"
  [m]
  (when (map? m)
    (let [paths (atom [])
          freqs (frequencies
                 (for [[k v] (tree-seq map? vals m)
                       :when (not (map? v))]
                   v))]
      (letfn [(walk [x path]
                (doseq [[k v] x]
                  (if (map? v)
                    (walk v (conj path k))
                    (swap! paths conj [(conj path k) v]))))]
        (walk m [])
        (when-let [[path _]
                   (some (fn [[path v]]
                           (when (= (get freqs v)
                                    (apply max (vals freqs)))
                             [path v]))
                         @paths)]
          path)))))