(defn Diff
  "Write a cljthon function to get the difference between two lists."
  [li1 li2]
  (let [s2 (set li2)]
    (remove s2 li1)))