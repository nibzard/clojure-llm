(defn strongest-extension
  "Given a prefix string and a possibly lazy sequence of candidate suffixes, return
  the prefix joined with the suffix that has the greatest score.

  The score of a suffix is:
  - +2 for each uppercase letter
  - +1 for each lowercase letter
  - 0 for any other character

  If two suffixes have the same score, choose the earliest one in the input.
  Ignore nil suffixes completely.

  The function must work correctly with infinite sequences by only realizing as
  much input as needed.

  Examples:
  (strongest-extension \"base\" [\"AA\" \"aB\" \"x!\"])
  ;; => \"base.AA\"

  (strongest-extension \"core\" [nil \"zz\" \"Z\" \"9Z\"])
  ;; => \"core.zz\""
  [prefix suffixes])