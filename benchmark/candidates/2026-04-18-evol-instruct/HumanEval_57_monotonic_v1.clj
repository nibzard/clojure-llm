(defn monotonic-slices
  "Return true if the vector can be split into contiguous slices of length 2 or more,
  where each slice is strictly monotonic (either increasing or decreasing), and the
  direction alternates between adjacent slices.

  Empty vector and single-element vector are considered true.

  >>> (monotonic-slices [])
  true
  >>> (monotonic-slices [1])
  true
  >>> (monotonic-slices [1 2 3 2 1])
  true
  >>> (monotonic-slices [1 3 5 4 2 6 7])
  true
  >>> (monotonic-slices [1 2 3 3 2])
  false"
  [v]
  (letfn [(dir [a b] (cond (< a b) :inc (> a b) :dec :else nil))
          (strict-monotonic? [xs]
            (let [d (dir (first xs) (second xs))]
              (and d
                   (every? #(= d (dir %1 %2)) xs (rest xs)))))
          (split-slices [xs]
            (loop [remaining xs
                   acc []]
              (if (< (count remaining) 2)
                (if (empty? remaining) acc nil)
                (let [d (dir (first remaining) (second remaining))]
                  (loop [i 2]
                    (cond
                      (= i (count remaining))
                      (conj acc remaining)

                      (not (= d (dir (nth remaining (dec i)) (nth remaining i))))
                      (let [slice (subvec (vec remaining) 0 i)]
                        (if (strict-monotonic? slice)
                          (let [next-remaining (subvec (vec remaining) i)]
                            (if (empty? next-remaining)
                              (conj acc slice)
                              (recur next-remaining (conj acc slice)))
                            )
                          nil))

                      :else
                      (recur (inc i))))))))]
    (or (empty? v)
        (= 1 (count v))
        (boolean (split-slices v)))))