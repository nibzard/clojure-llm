(defn find_Element
  "	Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [n (count arr)]
    (if (or (nil? arr) (zero? n))
      nil
      (let [rot (mod (or rotations 0) n)
            idx (mod (or index 0) n)
            shifted-idx (mod (+ idx rot) n)]
        (nth arr shifted-idx)))))