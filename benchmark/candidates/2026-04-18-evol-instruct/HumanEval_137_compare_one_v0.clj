(defn longest-common-prefix
  "Return the longest common prefix of a collection of strings.

  If the collection is empty, return \"\".
  If any string is nil, treat it as \"\".
  Preserve the exact prefix as it appears in the input strings.

  Examples:
  >>> (longest-common-prefix [\"flower\" \"flow\" \"flight\"])
  \"fl\"
  >>> (longest-common-prefix [\"dog\" \"racecar\" \"car\"])
  \"\"
  >>> (longest-common-prefix [\"interspecies\" nil \"interstellar\"])
  \"\"
  >>> (longest-common-prefix [])
  \"\""
  [strings]
  (let [strings (map #(or % "") strings)
        shortest (apply min-key count strings)]
    (loop [idx 0]
      (if (= idx (count shortest))
        shortest
        (let [prefix (subs shortest 0 (inc idx))]
          (if (every? #(clojure.string/starts-with? % prefix) strings)
            (recur (inc idx))
            (subs shortest 0 idx)))))))