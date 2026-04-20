(defn Diff
  "	Write a cljthon function to get the difference between two lists."
  [li1 li2]
  (filterv #(not (some #{%} li2)) li1))