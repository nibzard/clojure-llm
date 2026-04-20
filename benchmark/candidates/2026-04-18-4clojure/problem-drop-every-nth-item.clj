(defn problem-drop-every-nth-item [a b]
  (keep-indexed (fn [i x] (when (not= 0 (mod (inc i) b)) x)) a))