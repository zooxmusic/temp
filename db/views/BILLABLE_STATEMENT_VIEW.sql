CREATE OR ALTER VIEW IMSOWNER.BILLABLE_ITEMS_VIEW
AS SELECT
      p.PRG_ID,
      p.PA_GEN_INVOICE_IND,
      pfl.LINE_ID,
      pfl.START_DT,
      pfl.END_DT,
      pfl.FREQUENCY

    FROM
      PROGRAMS p,
      PROGRAM_FIXVAR_LINES pfl

    WHERE UPPER(p.PA_GEN_INVOICE_IND) = 'Y'
    AND pfl.FREQUENCY IS NOT NULL
    AND p.PRG_ID = pfl.PRG_ID