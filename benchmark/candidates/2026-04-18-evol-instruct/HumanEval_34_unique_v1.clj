(defn unique-by
  "Return the unique elements of coll, preserving the first occurrence of each value
after applying f. The result is returned in the original encounter order.

>>> (unique-by count [\"a\" \"bb\" \"c\" \"dd\" \"eee\" \"f\"])
[\"a\" \"bb\" \"eee\"]
>>> (unique-by even? [1 3 5 2 4 6 7 8])
[1 2]"
  [f coll]
  (loop [seen #{} xs coll out []]
    (if-let [x (first xs)]
      (let [k (f x)]
        (if (contains? seen k)
          (recur seen (rest xs) out)
          (recur (conj seen k) (rest xs) (conj out x))))
      out)))