(defn Diff
  "	Write a cljthon function to get the difference between two lists."
  [li1 li2]
  (set li1)
  (set li2)
  (clojure.set/difference (set li1) (set li2)))