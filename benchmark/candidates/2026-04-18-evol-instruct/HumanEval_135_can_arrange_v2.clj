(defn can-arrange-key
  "Given a map of keywords to numeric values, return the largest key (by
  keyword name order) whose value is less than or equal to the value of the
  key immediately before it in keyword-sorted order. If no such key exists,
  return nil.

  The map may contain nil values, which should be treated as smaller than any
  number.

  Examples:
  >>> (can-arrange-key {:a 1 :b 2 :c 4 :d 3 :e 5})
  :d
  >>> (can-arrange-key {:x 10 :y 20 :z 30})
  nil
  >>> (can-arrange-key {:a nil :b 1 :c 0})
  :c"
  [m]
  (let [pairs (sort-by key m)
        vals  (map (fn [[_ v]] (or v Long/MIN_VALUE)) pairs)]
    (loop [ks (map key pairs)
           vs vals
           prev nil
           idx 0
           last-bad nil]
      (if (empty? ks)
        last-bad
        (let [k (first ks)
              v (first vs)]
          (if (and prev (<= v prev))
            (recur (rest ks) (rest vs) v (inc idx) k)
            (recur (rest ks) (rest vs) v (inc idx) last-bad)))))))