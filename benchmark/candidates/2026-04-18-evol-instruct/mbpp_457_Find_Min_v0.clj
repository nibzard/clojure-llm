(defn min-width-sublist
  "Return the shortest sublist in `coll` that contains at least one occurrence of every distinct element in `coll`.

  If `coll` is nil or empty, return nil.

  Examples:
  (min-width-sublist [1 2 1 3 2])
  ;; => [1 2 1 3]

  (min-width-sublist '(\"a\" \"b\" \"a\"))
  ;; => (\"a\" \"b\" \"a\")"
  [coll]
  (when-let [v (seq coll)]
    (let [target (set v)
          n (count v)]
      (loop [i 0
             best nil]
        (if (= i n)
          best
          (let [suffix (subvec (vec v) i)
                [j _]
                (loop [j 0
                       seen #{}]
                  (if (= j (count suffix))
                    [nil seen]
                    (let [seen' (conj seen (nth suffix j))]
                      (if (= seen' target)
                        [j seen']
                        (recur (inc j) seen')))))
                candidate (when j (subvec (vec v) i (+ i j 1)))]
            (recur (inc i)
                   (cond
                     (nil? candidate) best
                     (nil? best) candidate
                     (< (count candidate) (count best)) candidate
                     :else best))))))))