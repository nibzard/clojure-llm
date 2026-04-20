(defn longest-palindrome-substring
  "Given a string s, return the longest palindromic substring in s.
If there are multiple with the same maximum length, return the one that appears first.

Examples:
(longest-palindrome-substring \"babad\") => \"bab\"
(longest-palindrome-substring \"cbbd\") => \"bb\"
(longest-palindrome-substring \"a\") => \"a\"
(longest-palindrome-substring \"\") => \"\""
  [s]
  (let [n (count s)
        expand (fn [l r]
                 (loop [l l r r]
                   (if (and (>= l 0) (< r n) (= (.charAt s l) (.charAt s r)))
                     (recur (dec l) (inc r))
                     [(inc l) (dec r)])))
        better (fn [[bl br] [l r]]
                 (let [b-len (inc (- br bl))
                       len   (inc (- r l))]
                   (cond
                     (> len b-len) [l r]
                     (and (= len b-len) (< l bl)) [l r]
                     :else [bl br])))]
    (if (zero? n)
      ""
      (let [[l r]
            (reduce (fn [best i]
                      (let [p1 (expand i i)
                            p2 (when (< i (dec n)) (expand i (inc i)))
                            best (better best p1)]
                        (if p2 (better best p2) best)))
                    [0 0]
                    (range n))]
        (subs s l (inc r))))))